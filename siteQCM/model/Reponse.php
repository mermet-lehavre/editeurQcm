<?php

class Reponse {

    private $id;
    private $proposition;
    private $correct;

    public function __construct($id, $proposition, $correct) {
        $this->id = $id;
        $this->proposition = $proposition;
        $this->correct = $correct;
    }

    public function getId()
    {
        return $this->id;
    }

    public function getProposition() {
        return $this->proposition;
    }

    public function getCorrect() {
        return $this->correct;
    }
}