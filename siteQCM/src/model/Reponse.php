<?php

class Reponse
{
    private $id;
    private $proposition;
    private $correct;
    private $choixEtudiant;

    public function __construct() {
        $a = func_get_args();
        $i = func_num_args();
        if (method_exists($this,$f='__construct'.$i)) {
            call_user_func_array(array($this,$f),$a);
        }
    }

    public function __construct3($id, $proposition, $correct)
    {
        $this->id = $id;
        $this->proposition = $proposition;
        $this->correct = $correct;
    }

    public function __construct4($id, $proposition, $correct, $choixEtudiant) {
        $this->id = $id;
        $this->proposition = $proposition;
        $this->correct = $correct;
        $this->choixEtudiant = $choixEtudiant;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getProposition()
    {
        return $this->proposition;
    }

    public function getCorrect()
    {
        return $this->correct;
    }

    public function getChoixEtudiant()
    {
        return $this->choixEtudiant;
    }

    public function setChoixEtudiant($choixEtudiant)
    {
        $this->choixEtudiant = $choixEtudiant;
    }
}
