SELECT pessoas.id, nome, MAX(ta.soma) as horas
	FROM pessoas INNER JOIN(
		SELECT pessoas.id as id_pessoa, tarefas.id_pessoa as tarefa_id, SUM(tarefas.duracao) as soma, RANK() OVER(
			ORDER BY MAX(tarefas.duracao) DESC
		) rank_n
	 	FROM pessoas, tarefas
		WHERE tarefas.prazo BETWEEN '01/01/2022' and '31/01/2022'
		GROUP BY pessoas.id, tarefas.id_pessoa
	)
	ta ON ta.tarefa_id = pessoas.id
	WHERE ta.rank_n = 1
	GROUP BY pessoas.id ORDER BY horas DESC;