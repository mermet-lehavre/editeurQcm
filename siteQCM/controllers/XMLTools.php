<?php
require_once("Etudiant.php");


class XMLTools
{

    private $document_xml;

    function XMLTools()
    {
        $this->document_xml = new DomDocument();
    }


    function existeQCM($fichier, $codeQCM)
    {
        if (isset($fichier)) {
            $this->document_xml->load($fichier);

            foreach($this->document_xml->getElementsByTagName('qcm') as $qcm) {
                $code =  $qcm->getElementsByTagName('code')->item(0)->nodeValue;

                if ($code === $codeQCM) {
                    return new Etudiant($qcm, $code);
                }
            }
        }
        return NULL;
    }

}