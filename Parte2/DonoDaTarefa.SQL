SELECT tarefas.titulo, COALESCE('Encaminhado para ' || pessoa.nome, 'Pendente') as Pessoa
	FROM tarefas LEFT JOIN pessoas pessoa ON pessoa.id = tarefas.id_pessoa
	ORDER BY tarefas.id ASC;