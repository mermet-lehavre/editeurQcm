<?php

class Question {

    private $enonce;
    private $reponses;

    public function __construct($enonce) {
        $this->enonce = $enonce;
        $this->reponses = array();
    }

    public function getEnonce() {
        return $this->enonce;
    }

    public function getReponses() {
        return $this->reponses;
    }

    public function addReponse($index, $response) {
        $this->reponses[$index] = $response;
    }


}