;
;--------Print decision-----------------------------------
;
(defrule AGENT::tell
    (declare (salience -50))
    (timp (valoare ?)) ;make sure it fires each cycle
    (ASK ?bprop)
    ; ?fcvd <- (ag_bel (bel_type moment) (bel_timeslice 0) (bel_pname ?bprop) (bel_pval ?bval))
    ?fcvd <- (ag_bel (bel_type moment) (bel_pname ?bprop) (bel_pval ?bval))
=>
    (printout t "         AGENT " ?bprop " " ?bval crlf)
    (retract ?fcvd)
)


;---------Delete instantaneous beliefs, i.e, those which are not fluents
(defrule AGENT::hk-eliminate-momentan-current-bel
    (declare (salience -95))
    (timp (valoare ?)) ;make sure it fires each cycle
    ; ?fmcb <- (ag_bel (bel_type moment) (bel_timeslice 0) (bel_pname ?p) (bel_pval ?v))
    ?fmcb <- (ag_bel (bel_type moment) (bel_pname ?p) (bel_pval ?v))
=>
    (if (eq ?*ag-in-debug* TRUE) then (printout t "    <D>hk-eliminate-momentan-current-bel " ?p " " ?v crlf))
    (retract ?fmcb)
)
