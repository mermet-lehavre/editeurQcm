<?php

class Utilitaire {

    private $document_xml;

    function lecture() {
        $document_xml = new DomDocument();
        $fichier = "nomDufichier.xml"; // ...

        if (!isset($_fichier)) {
            $document_xml->loadXML('nomDufichier.xml');
        }
    }

}

?>
