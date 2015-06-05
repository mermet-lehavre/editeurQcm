<?php

function sortArray($qcm1, $qcm2) {
    $etudiant1 = $qcm1->getEtudiant();
    $etudiant2 = $qcm2->getEtudiant();

    $compare = strcmp($etudiant1->getNom(), $etudiant2->getNom());
    if ($compare == 0) {
        $compare = strcmp($etudiant1->getPrenom(), $etudiant2->getPrenom());
    }
    return $compare;
}
