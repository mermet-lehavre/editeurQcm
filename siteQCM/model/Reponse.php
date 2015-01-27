<?php

class Reponse {

    private $proposition;
    private $correct;
    private $choixEtudiant;

    public function __construct($proposition, $correct) {
        $this->proposition = $proposition;
        $this->correct = $correct;
    }

    public function getProposition() {
        return $this->proposition;
    }

    public function getCorrect() {
        return $this->correct;
    }

    public function getChoixEtudiant() {
        return $this->choixEtudiant;
    }

    public function setChoixEtudiant($choixEtudiant)
    {
        $this->choixEtudiant = $choixEtudiant;
    }

}