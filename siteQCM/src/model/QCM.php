<?php

class QCM {

    private $titre;
    private $date;
    private $duree;
    private $avantPropos;
    private $parties;
    private $etudiant;

    public function __construct($titre, $date, $duree, $avantPropos, $etudiant) {
        $this->titre = $titre;
        $this->date = $date;
        $this->duree = $duree;
        $this->avantPropos = $avantPropos;
        $this->etudiant = $etudiant;
        $this->parties = array();
    }

    public function getTitre() {
        return $this->titre;
    }

    public function getDuree() {
        return $this->duree;
    }

    public function getAvantPropos() {
        return $this->avantPropos;
    }

    public function getDate() {
        return $this->date;
    }

    public function getParties() {
        return $this->parties;
    }

    public function getEtudiant() {
        return $this->etudiant;
    }

    public function addPartie($index, $partie) {
        $this->parties[$index] = $partie;
    }

}