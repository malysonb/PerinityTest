SELECT departamentos.id, departamentos.titulo, SUM((ta.finalizado)::int) as finalizados, SUM(CASE WHEN NOT ta.finalizado then 1 else 0 end) as nao_finalizados
	FROM departamentos INNER JOIN tarefas ta ON ta.id_departamento = departamentos.id
	GROUP BY departamentos.id, departamentos.titulo
	ORDER BY departamentos.id;