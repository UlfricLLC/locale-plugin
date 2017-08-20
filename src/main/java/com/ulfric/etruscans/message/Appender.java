package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import java.util.function.BiFunction;

public interface Appender extends BiFunction<Node, CompiledMessage, CompiledMessage> {

}