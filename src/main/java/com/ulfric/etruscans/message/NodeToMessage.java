package com.ulfric.etruscans.message;

import org.w3c.dom.Node;

import java.util.function.BiFunction;

interface NodeToMessage extends BiFunction<Node, CompiledMessage, Result> {

}
