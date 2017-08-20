package com.ulfric.etruscans.message;

interface Result { // TODO better results system

	class Continue implements Result {
		private final CompiledMessage base;

		Continue() {
			this(null);
		}

		Continue(CompiledMessage base) {
			this.base = base;
		}

		public CompiledMessage getContinuation() {
			return base;
		}
	}

}