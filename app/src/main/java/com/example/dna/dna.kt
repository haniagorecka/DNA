package com.example.dna

/**
 * @author Hanna Górecka
 * Funkcja zwraca nić komplementarną do podanej nici kodującej DNA
 * @param kodujaca nić kodująca DNA, na początku sprawdza czy nie jest pusta
 * @return komplementarna nić matrycowa (odwrócona kolejność)
 * @throws Exception jeśli znajdzie niewłaściwy znak w nici kodującej
 */
fun komplement(kodujaca: String): String
{
    check(kodujaca.isNotBlank()){"Nić kodująca jest pusta"}
    var komplementarna = ""
   for(el in kodujaca.uppercase())
   {
       komplementarna += when(el) {
           'A' -> 'T'
           'C' -> 'G'
           'T' -> 'A'
           'G' -> 'C'
           else -> throw Exception(" Niewłaściwy symbol w nici kodującej")

       }

   }
    return komplementarna.reversed()
}

/**
 * @author Hanna Górecka
 * Funkcja zwraca nić transkrybowaną RNA do podanej nici matrycowej
 * @param matrycowa nić matrycowa DNA, na początku sprawdza czy nie jest pusta
 * @return transkrybowana nić RNA
 * @throws Exception jeśli znajdzie niewłaściwy znak w nici matrycowej
 */
fun transkrybuj (matrycowa: String): String
{
    check(matrycowa.isNotBlank()){"Nić matrycowa jest pusta"}
    var transkrybowana = ""
    for(el in matrycowa.uppercase())
    {
        transkrybowana += when(el) {
            'A' -> 'U'
            'C' -> 'G'
            'T' -> 'A'
            'G' -> 'C'
            else -> throw Exception(" Niewłaściwy symbol w nici matrycowej")

        }

    }
    return transkrybowana.reversed()
}

/**
 * @author Hanna Górecka
 * Funkcja zwraca sekwencję aminokwasów (białko) na podstawie nici RNA
 * @param rna nić RNA, na podstawie, której translowane jest białko, na początku sprawdza czy nie jest pusta
 * @return sekwencja aminokwarów tworzących białko
 * @throws Exception jeśli znajdzie nieistniejący kodon
 */
fun transluj (rna: String):String
{
    check(rna.isNotBlank()){"Nić rna jest pusta"}

    val mapaAminokwasow= mapOf<String, String>(
       "UUU" to "Phe", "UUC" to "Phe", "UUA" to "Leu", "UUG" to "Leu",
        "CUU" to "Leu", "CUC" to "Leu", "CUA" to "Leu", "CUG" to "Leu",
        "AUU" to "Ile", "AUC" to "Ile", "AUA" to "Ile", "AUG" to "Met",
        "GUU" to "Val", "GUC" to "Val", "GUA" to "Val", "GUG" to "Val",
        "UCU" to "Ser", "UCC" to "Ser", "UCA" to "Ser", "UCG" to "Ser",
        "CCU" to "Pro", "CCC" to "Pro", "CCA" to "Pro", "CCG" to "Pro",
        "ACU" to "Thr", "ACC" to "Thr", "ACA" to "Thr", "ACG" to "Thr",
        "GCU" to "Ala", "GCC" to "Ala", "GCA" to "Ala", "GCG" to "Ala",
        "UAU" to "Tyr", "UAC" to "Tyr", "UAA" to "Stop", "UAG" to "Stop",
        "CAU" to "His", "CAC" to "His", "CAA" to "Gln", "CAG" to "Gln",
        "AAU" to "Asn", "AAC" to "Asn", "AAA" to "Lys", "AAG" to "Lys",
        "GAU" to "Asp", "GAC" to "Asp", "GAA" to "Glu", "GAG" to "Glu",
        "UGU" to "Cys", "UGC" to "Cys", "UGA" to "Stop", "UGG" to "Trp",
        "CGU" to "Arg", "CGC" to "Arg", "CGA" to "Arg", "CGG" to "Arg",
        "AGU" to "Ser", "AGC" to "Ser", "AGA" to "Arg", "AGG" to "Arg",
        "GGU" to "Gly", "GGC" to "Gly", "GGA" to "Gly", "GGG" to "Gly"
    )

    var i: Int = 0
var aminokwasy = ""

    while (i+3<=rna.length)
    {
        val temp = rna.uppercase().subSequence(i, i+3)


        if(mapaAminokwasow.containsKey(temp.toString()))
        {
            if(mapaAminokwasow.getValue(temp.toString())=="Stop"){ break}
            else{
                aminokwasy+=mapaAminokwasow.getValue(temp.toString())
                aminokwasy+=" "
                i+=3
            }
        }
        else throw Exception("Niewłaściwy kodon w nici RNA")


    }
    return aminokwasy

}

/**
 * Funkcja testująca funkcji komplement()
 */
fun testKomplement()
{
    assert(komplement("aatgttg")=="CAACATT", {"Test funkcji komplement zakończony niepowodzeniem"})
    println("Test funkcji komplement zakończony powodzeniem")
}

/**
 * Funkcja testująca funkcji transkrybuj()
 */
fun testTranskrybuj()
{
    assert(transkrybuj("aatgttg") =="CAACAUU", {"Test funkcji transkrybuj zakończony niepowodzeniem"})
    println("Test funkcji transkrybuj zakończony powodzeniem")
}
/**
 * Funkcja testująca funkcji transluj()
 */
fun testTransluj()
{
    assert(transluj("aauguug") =="Asn Val ", {"Test funkcji transluj zakończony niepowodzeniem"})
    println("Test funkcji transluj zakończony powodzeniem")
}
fun main()
{
  testKomplement()
    testTranskrybuj()
    testTransluj()

}