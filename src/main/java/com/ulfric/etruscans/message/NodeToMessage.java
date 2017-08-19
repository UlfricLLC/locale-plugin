package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import java.util.function.Function;

interface NodeToMessage extends Function<Node, CompiledMessage> {

}
