<?php

class Etudiant {

    private $code;
    private $nom;
    private $prenom;
    private $note;

    public function __construct($code) {
        $this->code = $code;
    }

    public function getCode() {
        return $this->code;
    }

    public function getNom() {
        return $this->nom;
    }

    public function setNom($nom) {
        $this->nom = $nom;
    }

    public function getPrenom() {
        return $this->prenom;
    }

    public function setPrenom($prenom) {
        $this->prenom = $prenom;
    }

    public function getNote() {
        return $this->note;
    }

    public function setNote($note) {
        $this->note = $note;
    }

}