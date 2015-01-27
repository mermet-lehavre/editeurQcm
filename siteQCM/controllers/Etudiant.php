<?php

class Etudiant {

    private $qcm;
    private $code;
    private $nom;
    private $prenom;
    private $note;

    function Etudiant($qcm, $code) {
        $this->qcm = $qcm;
        $this->code = $code;
    }

    public function getQCM()
    {
        return $this->$qcm;
    }

    public function getCode()
    {
        return $this->code;
    }

    public function getNom()
    {
        return $this->nom;
    }

    public function setNom($nom)
    {
        $this->nom = $nom;
    }

    public function getPrenom()
    {
        return $this->prenom;
    }

    public function setPrenom($prenom)
    {
        $this->prenom = $prenom;
    }

    public function getNote()
    {
        return $this->note;
    }

    public function setNote($note)
    {
        $this->note = $note;
    }



}