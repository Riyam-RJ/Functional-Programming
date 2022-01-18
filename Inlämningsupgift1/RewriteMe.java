package Inlämningsupgift1;

import java.util.*;
import java.util.stream.Collectors;

/*
Inlämningsuppgit i kursen Funktionell Programmering för JAVA-programmet
För samtliga funktioner i denna fil, byt ut "throw UnSupportedException"-raden
och skriv ett pipeline-uttryck som returnerar det som ska returneras.

Streams MÅSTE användas i samtliga funktioner som lämnas in
För att testa om era funktioner funkar, kör testerna som hör till denna fil
För att bli godkänd på denna uppgift måste minst 7 av funktionerna vara implementerade.

Sigruns bedömning av denna uppgift kommer att gå till så att hon klipper in er version av denna fil
i sitt projekt och kör testerna.

Hennes kontroll om ni har klarat uppgifterna eller inte görs genom att
hon kollar att tillräckeligt många av tester går gröna. Pga detta ska ni inte ändra i någon fil
medföljande detta projekt, och inte heller metodsignaturerna i denna fil eller era tester, eftersom
ni då riskerar att påverka rättningen i negativ riktning.
 */

public class RewriteMe {

    public Database database = new Database();
    public List<Question> questions = database.getQuestions();

    //Skriv en funktioner som returnerar hur många frågor det finns i databasen?
    public int getAmountOfQuestionsInDatabase(){

        List<Question> questions = database.getQuestions();

        int amount = (int) questions.stream().count();

        return amount;

    }

    //Hur många frågor finns i databasen för en viss, given kategori (som ges som inparameter)
    public int getAmountOfQuestionsForACertainCategory(Category category){
        List<Question> questionsInDatabase = database.getQuestions();

        int result = (int) questionsInDatabase.stream().filter(question -> category == question.getCategory()).count();
        return result;

    }

    //Skapa en lista innehållandes samtliga frågesträngar i databasen
    public List<String> getListOfAllQuestions(){
        List<Question> questionsInDatabase = database.getQuestions();

        List<String> result = questionsInDatabase.stream().map(question -> question.getQuestionString()).collect(Collectors.toList());

        return result;
    }

    //Skapa en lista innehållandes samtliga frågesträngar där frågan tillhör en viss kategori
    //Kategorin ges som inparameter
    public List<String> getAllQuestionStringsBelongingACategory(Category category){
        List<Question> questionsInDatabase = database.getQuestions();

        List<String> result = questionsInDatabase.stream().filter(question -> question.getCategory() == category).map(Question::getQuestionString).collect(Collectors.toList());

        return result;

    }

    //Skapa en lista av alla svarsalternativ, där varje svarsalternativ får förekomma
    // en och endast en gång i den lista som du ska returnera
    public List<String> getAllAnswerOptionsDistinct(){
        List<Question> questionsInDatabase = database.getQuestions();
        Set<String> set = new HashSet<>();

        questionsInDatabase.stream().map(Question::getAllAnswers).forEach(set::addAll);

        List<String> result = new ArrayList<>(set);
        return result;

    }


    //Finns en viss sträng, given som inparameter, som svarsalternativ till någon fråga i vår databas?
    public boolean isThisAnAnswerOption(String answerCandidate){
        List<Question> questionsInDatabase = database.getQuestions();

        return questionsInDatabase.stream().anyMatch(question -> question.getAllAnswers().contains(answerCandidate));

    }

    //Hur ofta förekommer ett visst svarsalternativ, givet som inparameter, i databasen
    public int getAnswerCandidateFrequncy(String answerCandidate){
        List<Question> questionsInDatabase = database.getQuestions();
        List<String> allCandidateAnswers = new ArrayList<>();

        questionsInDatabase.stream().map(Question::getAllAnswers).forEach(allCandidateAnswers::addAll);

        int frequency = (int) allCandidateAnswers.stream().filter(element -> element.equals(answerCandidate)).count();

        return frequency;
    }

    //Skapa en Map där kategorierna är nycklar och värdena är en lista
    //av de frågesträngar som tillhör varje kategori
    public Map<Category, List<String>> getQuestionGroupedByCategory(){
        List<Question> questionsInDatabase = database.getQuestions();
        Map<Category, List<String>> result = new HashMap<>();

        questionsInDatabase.forEach(question -> {
            if (!result.containsKey(question.getCategory())) {
                result.put(question.getCategory(), new ArrayList<>());
            }
            result.get(question.getCategory()).add(question.getQuestionString());
        });

        return result;
    }

    //Skapa en funktion som hittar det svarsalternativ som har flest bokstäver, i en kategori, given som inparameter
    // OBS: Du måste använda Reduce!
    public String getLongestLettercountAnwerInAGivenCategory(Category c)
    {
        List<Question> questionsInDatabase = database.getQuestions();
        final String[] result = {null};

        questionsInDatabase.forEach(question -> {
            if(question.getCategory() == c){
                List<String> allAnswers = question.getAllAnswers();
                Optional<String> reduce = allAnswers.stream().reduce((max1, max2) -> max1.length() >= max2.length() ? max1 : max2);
                reduce.ifPresent(s -> result[0] = s);
            }
        });

        return result[0];
    }


    public static void main(String[] args){
        RewriteMe uppg4 = new RewriteMe();

    }

}