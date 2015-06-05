<?php

class Question {

    private $id;
    private $enonce;
    private $reponses;

    public function __construct($id, $enonce) {
        $this->id = $id;
        $this->enonce = $enonce;
        $this->reponses = array();
    }

    public function getId() {
        return $this->id;
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

    public function findReponse($idReponse) {
        foreach($this->reponses as $reponse) {
            if ($reponse->getId() == $idReponse) {
                return $reponse;
            }
        }
        return NULL;
    }
}