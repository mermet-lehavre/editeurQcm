<?php

class Partie {

    private $titre;
    private $questions;

    public function Partie($titre) {
        $this->titre = $titre;
        $this->questions = array();
    }

    public function getQuestions() {
        return $this->questions;
    }

    public function getTitrePartie() {
        return $this->titre;
    }

    public function addQuestion($index, $question) {
        $this->questions[$index] = $question;
    }
}