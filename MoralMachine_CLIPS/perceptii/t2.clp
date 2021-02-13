;Scenariul 2


;Part 1
(ag_percept (percept_pobj e1) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj ped1) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped2) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped3) (percept_pname isa) (percept_pval pedestrian))


(ag_percept (percept_pobj e1) (percept_pname direction) (percept_pval left))

(ag_percept (percept_pobj ped1) (percept_pname age) (percept_pval 70))

(ag_percept (percept_pobj ped2) (percept_pname age) (percept_pval 75))

(ag_percept (percept_pobj ped3) (percept_pname age) (percept_pval 73))


(ag_percept (percept_pobj ped1) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj ped2) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj ped3) (percept_pname partof) (percept_pval e1))


;Part 2
(ag_percept (percept_pobj e2) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj ped4) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped5) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped6) (percept_pname isa) (percept_pval pedestrian))


(ag_percept (percept_pobj e2) (percept_pname direction) (percept_pval ahead))

(ag_percept (percept_pobj ped4) (percept_pname age) (percept_pval 8))

(ag_percept (percept_pobj ped5) (percept_pname age) (percept_pval 35))

(ag_percept (percept_pobj ped6) (percept_pname age) (percept_pval 10))


(ag_percept (percept_pobj ped4) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj ped5) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj ped6) (percept_pname partof) (percept_pval e2))