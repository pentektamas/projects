;Scenariul 3


;Part 1
(ag_percept (percept_pobj e1) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj ped1) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped2) (percept_pname isa) (percept_pval pedestrian))

(ag_percept (percept_pobj ped3) (percept_pname isa) (percept_pval pedestrian))


(ag_percept (percept_pobj e1) (percept_pname direction) (percept_pval left))

(ag_percept (percept_pobj ped1) (percept_pname age) (percept_pval 30))

(ag_percept (percept_pobj ped2) (percept_pname age) (percept_pval 45))

(ag_percept (percept_pobj ped3) (percept_pname age) (percept_pval 37))


(ag_percept (percept_pobj ped1) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj ped2) (percept_pname partof) (percept_pval e1))

(ag_percept (percept_pobj ped3) (percept_pname partof) (percept_pval e1))


;Part 2
(ag_percept (percept_pobj e2) (percept_pname isa) (percept_pval event))

(ag_percept (percept_pobj d1) (percept_pname isa) (percept_pval dog))

(ag_percept (percept_pobj d2) (percept_pname isa) (percept_pval dog))

(ag_percept (percept_pobj c1) (percept_pname isa) (percept_pval cat))


(ag_percept (percept_pobj e2) (percept_pname direction) (percept_pval ahead))


(ag_percept (percept_pobj d1) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj d2) (percept_pname partof) (percept_pval e2))

(ag_percept (percept_pobj c1) (percept_pname partof) (percept_pval e2))