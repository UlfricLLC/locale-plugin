package com.ulfric.etruscans.message;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.bukkit.ChatColor;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.xml.XmlHelper;
import com.ulfric.fancymessage.Message;
import com.ulfric.i18n.content.Detail;
import com.ulfric.i18n.content.Details;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompiledMessage extends AggregatingMessagePart { // TODO flatten messages to save bandwidth

	public static void main(String[] args) {
		//CompiledMessage test = compile("<red>this is in red <green><bold>and this is green</bold></green><bold> but this is red</bold></red>");
		//CompiledMessage test = compile("<hover value=\"Hovering over all\">Hover over me!</hover>");
		CompiledMessage test = compile("<foreach value=\"vals\">${val}</foreach>");
		Details details = Details.of(Detail.single("vals", Arrays.asList("one", "two", "three")));
		Message display = test.toMessage(null, details);
		System.out.println(display);
	}

	private static final Pattern PLACEHOLDER = Pattern.compile("\\$\\{[a-zA-Z0-9_.]+\\}");
	private static final Map<String, NodeToMessage> TRANSFORMERS = new HashMap<>();

	static {
		registerColors();
		TRANSFORMERS.put("foreach", NodeToForEach.INSTANCE);
		TRANSFORMERS.put("hover", NodeToHover.INSTANCE);
	}

	private static void registerColors() {
		for (ChatColor color : ChatColor.values()) {
			String name = color.getName().toLowerCase();
			if (color.isColor()) {
				registerColor(name, color);
			} else {
				registerFormat(name, color);
			}
		}
	}

	private static void registerColor(String name, ChatColor color) {
		NodeToColor function = new NodeToColor(color);
		TRANSFORMERS.put(name, function);
		TRANSFORMERS.put(name.replace("_", ""), function);
	}

	private static void registerFormat(String name, ChatColor format) {
		switch (format) { // TODO remove switch
			case BOLD:
				TRANSFORMERS.put(name, NodeToBold.INSTANCE);
				break;

			case ITALIC:
				TRANSFORMERS.put(name, NodeToItalic.INSTANCE);
				break;

			case MAGIC:
				TRANSFORMERS.put(name, NodeToMagic.INSTANCE);
				break;

			case STRIKETHROUGH:
				TRANSFORMERS.put(name, NodeToStrikethrough.INSTANCE);
				break;

			case UNDERLINE:
				TRANSFORMERS.put(name, NodeToUnderline.INSTANCE);
				break;

			case RESET:
				// TODO can we use this?
				break;

			default:
				// TODO log error
				break;
		}
	}

	public static CompiledMessage compile(String message) {
		Element root = XmlHelper.parseIncompleteDocument(message).getDocumentElement();
		return compile(root);
	}

	static CompiledMessage compile(Node message) { // TODO
		CompiledMessage base = new CompiledMessage(CompiledMessage.emptyMessage());
		Result result = addNodeToBase(base, message);

		String value = XmlHelper.getNodeValue(message);
		if (value != null) {
			return base;
		}

		if (result == Result.CONTINUE) {
			XmlHelper.asList(message.getChildNodes())
			.stream()
			.map(CompiledMessage::compile)
			.forEach(base.parts::add);
		}

		return base;
	}

	private static CompiledMessage compilePlainMessage(String message) { // TODO cleanup, this method is too complex
		Message plain = CompiledMessage.emptyMessage();
		CompiledMessage compiled = new CompiledMessage(plain);
		if (message == null) {
			plain.setText("");
		} else {
			Matcher variables = PLACEHOLDER.matcher(message);
			if (variables.find()) {

				int textStart = 0;
				do {
					String text = message.substring(textStart, variables.start());
					if (!text.isEmpty()) {
						Message part = new Message();
						part.setText(text);
						compiled.parts.add(new PlainMessagePart(part));
					}
					textStart = variables.end();

					String variable = variables.group();
					compiled.parts.add(new PlaceholderMessagePart(variable));
				} while (variables.find());
			} else {
				plain.setText(message);
			}
		}
		return compiled;
	}

	public static CompiledMessage wrap(Message message) {
		Message copy = new Message(message);
		if (copy.getText() == null) {
			copy.setText("");
		}
		return new CompiledMessage(copy);
	}

	private static Result addNodeToBase(CompiledMessage base, Node message) {
		NodeToMessage function = TRANSFORMERS.get(message.getNodeName().toLowerCase());

		if (function == null) {
			String value = XmlHelper.getNodeValue(message);
			if (value == null) {
				return Result.CONTINUE;
			}
			base.parts.add(compilePlainMessage(value));
			return Result.CONTINUE;
		}

		if (StringUtils.isEmpty(base.base.getText())) {
			String value = XmlHelper.getNodeValue(message);
			if (!StringUtils.isEmpty(value)) {
				base.base.setText(value);
			}
		}

		return function.apply(message, base);
	}

	protected static final Message emptyMessage() {
		Message message = new Message();
		message.setText("");
		return message;
	}

	protected final Message base;

	CompiledMessage(Message base) {
		super(new ArrayList<>());

		Objects.requireNonNull(base, "base");

		this.base = base;
		if (base.getText() == null) {
			throw new IllegalStateException();
		}
	}

	@Override
	protected Message createNewBase() {
		return new Message(base);
	}

}
