var enterTab = (function() {
	var fn = {};
	// APLICAR AO FORMULARIO INTEIRO
	fn.aplicar = function() {
		jQuery('body')
				.on(
						'keydown',
						'input, select',
						function(e) {
							var self = $(this), form = self
									.parents('form:eq(0)'), focusable, next;
							if (e.keyCode == 13) {
								focusable = form.find('input,a,select,button')
										.filter(':visible');
								focusable.prop('disabled', false);
								next = focusable.eq(focusable.index(this) + 1);
								if (next.length) {
									next.focus();
									next.select();
								} else {
									form.submit();
								}
								return false;
							}
						});
	};

	// CANCELA EVENTOS CRIADOS NA FUNCAO APLICAR
	fn.desaplicar = function() {
		jQuery('body').off('keydown', 'input, select');
	};

	// APLICAR SOMENTE AO CAMPO NO EVENTO ONKEYDOWN
	fn.aplicarIndividual = function(e, idProximo) {
		if (e != null && e.keyCode == 13) {
			e.preventDefault();
			$next = $("#frmGeral\\:" + idProximo);
			if ($next.children()) {
				if ($next[0].attributes.type == "submit") {
					$("button[" + $next[0].attributes.id + "]").focus();
				} else {
					var input = $next.children()[0];
					input.focus();
				}
			}
		}
	}
	return fn;
})();