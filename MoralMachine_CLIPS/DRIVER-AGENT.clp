
; EXECUTION TIME 

(defrule AGENT::time_start "Credit: Portik Annamaria & Stan Lavinia, CTI 2019"
  (declare (salience 92))
  (timp (valoare ?t))
=>
  (assert (tstart (time))))


(defrule AGENT::time_end
  (declare (salience -92))
  ?fsta <- (tstart ?time_start)
=>
  (bind ?time_end (time))
  (bind ?ex_time (- ?time_end ?time_start))
  (if (eq ?*ag-measure-time* TRUE) then (printout t "            <M> AGENT Decision time: " ?ex_time " sec." crlf))
  (retract ?fsta))

;AUXILIARY FUNCTIONS

(deffunction AGENT::sum_of_ages (?e)
    (bind ?sum 0)
    (do-for-all-facts ((?fact ag_percept)) (and (eq ?fact:percept_pval ?e) (eq ?fact:percept_pname partof))
        (do-for-all-facts ((?selectedFact ag_percept)) (and (eq ?selectedFact:percept_pobj ?fact:percept_pobj) (eq ?selectedFact:percept_pname age)) 
            (bind ?sum (+ ?sum ?selectedFact:percept_pval))
        )
    )   
    (return ?sum)
)

(deffunction AGENT::nr_of_animals (?e)
    (bind ?sum 0)
    (do-for-all-facts ((?fact ag_percept)) (and (eq ?fact:percept_pval ?e) (eq ?fact:percept_pname partof))
        (do-for-all-facts ((?selectedFact ag_percept)) (and (eq ?selectedFact:percept_pobj ?fact:percept_pobj) (eq ?selectedFact:percept_pname isa) (or (eq ?selectedFact:percept_pval dog) (eq ?selectedFact:percept_pval cat) )  ) 
            (bind ?sum (+ ?sum 1))
        )
    )   
    (return ?sum)
)

(deffunction AGENT::nr_of_pedestrians (?e)
    (bind ?sum 0)
    (do-for-all-facts ((?fact ag_percept)) (and (eq ?fact:percept_pval ?e) (eq ?fact:percept_pname partof))
        (do-for-all-facts ((?selectedFact ag_percept)) (and (eq ?selectedFact:percept_pobj ?fact:percept_pobj) (eq ?selectedFact:percept_pname isa) (eq ?selectedFact:percept_pval pedestrian)) 
            (bind ?sum (+ ?sum 1))
        )
    )   
    (return ?sum)
)

; RULES FOR DIFFERENT SCENARIO

; scene 1 - ahead red light, left barrier => ahead
(defrule AGENT::red_light_and_barrier

    (ag_percept (percept_pobj ?e1) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?sem) (percept_pname isa) (percept_pval semaphore))
    (ag_percept (percept_pobj ?sem) (percept_pname partof) (percept_pval ?e1))
    (ag_percept (percept_pobj ?sem) (percept_pname color) (percept_pval red))
    (ag_percept (percept_pobj ?e1) (percept_pname direction) (percept_pval ?dir))

    (ag_percept (percept_pobj ?e2) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?bar) (percept_pname isa) (percept_pval barrier))
    (ag_percept (percept_pobj ?bar) (percept_pname partof) (percept_pval ?e2))

    =>

    (assert (ag_bel (bel_type moment) (bel_pname move-on-maneuver ) (bel_pval ?dir)))
)


;scene 2 - ahead youg people, left old people => left
(defrule AGENT::young_old_people
    
;    (timp (valoare ?t))
    (ag_percept (percept_pobj ?e1) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?e1) (percept_pname direction) (percept_pval ?dir1))
    (ag_percept (percept_pobj ?e2) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?e2) (percept_pname direction) (percept_pval ?dir2))

    ;adaug ca sa verific ca in ambele parti am pedestrian
    (ag_percept (percept_pobj ?ped1) (percept_pname isa) (percept_pval pedestrian))
    (ag_percept (percept_pobj ?ped1) (percept_pname partof) (percept_pval ?e1))
    (ag_percept (percept_pobj ?ped2) (percept_pname isa) (percept_pval pedestrian))
    (ag_percept (percept_pobj ?ped2) (percept_pname partof) (percept_pval ?e2))

    (test (and (not (eq ?e1 ?e2)) (not (eq (sum_of_ages ?e1) (sum_of_ages ?e2)))))

    =>

    (bind ?sum1 (sum_of_ages ?e1))
    (bind ?sum2 (sum_of_ages ?e2))
    (if (> ?sum1 ?sum2) then 
        (assert (ag_bel (bel_type moment) (bel_pname move-on-maneuver ) (bel_pval ?dir1))) 
    else
        (assert (ag_bel (bel_type moment) (bel_pname move-on-maneuver ) (bel_pval ?dir2)))
    )
)

;scene 3 - ahead animals, left people => ahead
(defrule AGENT::animal_people 

    (ag_percept (percept_pobj ?e1) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?e1) (percept_pname direction) (percept_pval ?dir1))
    (ag_percept (percept_pobj ?e2) (percept_pname isa) (percept_pval event))
    (ag_percept (percept_pobj ?e2) (percept_pname direction) (percept_pval ?dir2))
    (test (and (not (eq ?e1 ?e2)) (not (eq (nr_of_animals ?e1) (nr_of_pedestrians ?e2)))))

    =>

    (bind ?nr_a1 (nr_of_animals ?e1))
    (bind ?nr_a2 (nr_of_animals ?e2))
    (bind ?nr_p1 (nr_of_pedestrians ?e1))
    (bind ?nr_p2 (nr_of_pedestrians ?e2))
     (if (and (> ?nr_a1 ?nr_a2) (< ?nr_p1 ?nr_p2)) then 
        (assert (ag_bel (bel_type moment) (bel_pname move-on-maneuver ) (bel_pval ?dir1))) 
    else
        (if (and (> ?nr_a2 ?nr_a1) (< ?nr_p2 ?nr_p1)) then
            (assert (ag_bel (bel_type moment) (bel_pname move-on-maneuver ) (bel_pval ?dir2)))
        )
    )
)