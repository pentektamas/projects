;Scenariul 1


;Part 1
(ag_percept (percept_pobj e1) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj ped1) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped2) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj sem1) (percept_pname isa) (percept_pval semaphore))


(ag_percept (percept_pobj e1) (percept_pname direction) (percept_pval ahead))

(ag_percept (percept_pobj ped1) (percept_pname age) (percept_pval 40))

(ag_percept (percept_pobj ped2) (percept_pname age) (percept_pval 45))

(ag_percept (percept_pobj sem1) (percept_pname color) (percept_pval red))


(ag_percept (percept_pobj ped1) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj ped2) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj sem1) (percept_pname partof) (percept_pval e1))


;Part 2
(ag_percept (percept_pobj e2) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj pas1) (percept_pname isa) (percept_pval passenger))

(ag_percept (percept_pobj pas2) (percept_pname isa) (percept_pval passenger))

(ag_percept (percept_pobj bar1) (percept_pname isa) (percept_pval barrier))


(ag_percept (percept_pobj e2) (percept_pname direction) (percept_pval left))

(ag_percept (percept_pobj pas1) (percept_pname age) (percept_pval 24))

(ag_percept (percept_pobj pas2) (percept_pname age) (percept_pval 30))


(ag_percept (percept_pobj pas1) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj pas2) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj bar1) (percept_pname partof) (percept_pval e2))