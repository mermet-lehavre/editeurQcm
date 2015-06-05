<?php

class Etudiant {

    private $code;
    private $nom;
    private $prenom;
    private $note;
    private $numero;

    public function __construct() {
        $a = func_get_args();
        $i = func_num_args();
        if (method_exists($this,$f='__construct'.$i)) {
            call_user_func_array(array($this,$f),$a);
        }
    }

    public function __construct1($code) {
        $this->code = $code;
    }

    public function __construct5($code, $nom, $prenom, $note, $numero) {
        $this->code = $code;
        $this->nom = $nom;
        $this->prenom = $prenom;
        $this->note = $note;
        $this->numero = $numero;
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

    public function getNumero() {
        return $this->numero;
    }

    public function setNumero($numero) {
        $this->numero = $numero;
    }

}
