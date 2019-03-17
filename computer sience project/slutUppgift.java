package test;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

public class slutUppgift {

    //alla arrays
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> mainList = new ArrayList<String>();
    private static ArrayList<String> encryptedList = new ArrayList<>();
    private static ArrayList<String> decryptedList = new ArrayList<>();
    private static ArrayList<Integer> numList = new ArrayList<Integer>();
    private static ArrayList<String> dateList = new ArrayList<>();

    public static void main(String[] args) {
        boolean quit = false;
        while (!quit) {
            menu();
            int actions = sc.nextInt();
            sc.nextLine();
            switch (actions) {
                case 1:
                    //add
                    addWord();
                    fibArrayAdder(mainList, numList, dateList);
                    break;
                case 2:
                    //search
                    searchList();
                    break;
                case 3:
                    //remove
                    removeList();
                    break;
                case 4:
                    //sort
                    sortMenu();
                    break;
                case 5:
                    //reverse
                    extraFunction();
                    break;
                case 6:
                    encrypt();
                    break;
                case 7:
                    countStrings();
                    break;
                case 8:
                    quit = true;
                    break;

            }
        }
    }

    private static void sortMenu() {
        System.out.println("1: Sort by length" + "\n" + "2: Sort by alphabet");
        int actions = sc.nextInt();
        switch (actions) {
            case 1:
                //längden på string
                lengthSort();

                break;
            case 2:
                //bokstavs ordning
                alphaSort();
                break;
        }
    }
    //by length
    //bubble sort
    private static void lengthSort() {
        System.out.println("Before: " + mainList + "\n");
        //går igenom array
        for(int i=0;i<mainList.size();i++){
            for(int j=0;j<mainList.size()-i-1;j++){
                if( (mainList.get(j)).length() > (mainList.get(j+1)).length() ){
                    //byter plats med varandra
                    Collections.swap(mainList, j, j+1);
                }
            }
        }
        for(String list : mainList){
            System.out.println(list);
        }
    }
    //alfabetisk ordning
    //bubblesort
    private static void alphaSort() {
        System.out.println("Before: " + mainList);
        String temp;
        //går igenom arrayen
        for (int i = 0; i < mainList.size(); i++) {
            for (int j = mainList.size() - 1; j > i; j--)
                if (mainList.get(i).compareTo(mainList.get(j)) < 0) {

                    //byter plats med varandra
                    temp = mainList.get(i);
                    mainList.set(i, mainList.get(j));
                    mainList.set(j, temp);
                }
        }
        //vänder på arrayen
        Collections.reverse(mainList);
        System.out.println("After: " + mainList);
    }

    private static void extraFunction() {
        System.out.println("Type a word you want reversed");
        String revInput = sc.next();
        String reversed = reverseString(revInput);
        System.out.println("Your reversed word is: " + reversed);
    }
    //rekursiv
    private static String reverseString(String revInput) {
        if (revInput.isEmpty()) {
            System.out.println("String in now Empty");
            return revInput;
        }
        //kallar functionen
        System.out.println(revInput.substring(1));
        return reverseString(revInput.substring(1)) + revInput.charAt(0);
    }
    private static void fibArrayAdder(ArrayList<String> stringList, ArrayList<Integer> numList, ArrayList<String> dateList) {
        if (numList.size() < 2) {
            //setting start value (first value of the fibonacci sequence)
            numList.add(1);
            dateList.add(new java.util.Date().toString());
            System.out.println(dateList);
            System.out.println("\n"+"Is it even? " + isEven(numList.get(0)));
        } else if (numList.size() >= 2) {
            // om stringArrayn är längre än 2 skall addering börja användas, den förra + den förrförra.
            int temp1 = stringList.size() - 3;
            int temp2 = stringList.size() - 2;
            int newNum = numList.get(temp1) + numList.get(temp2);
            numList.add(newNum);
            System.out.println("\n" + "Is it even? " + isEven(newNum));
            dateList.add(new java.util.Date().toString());
            System.out.println(dateList);
        }
        System.out.println("\n" + "Fibonacci array " + numList);
    }

    //kollar om senaste nummret i fib. är jämn eller inte.
    private static boolean isEven(int input) {
        if (input % 2 == 0) {
            return true;
        }
        return false;
    }

    private static void encrypt() {
        System.out.println("1: encrypt" + "\n" + "2: dekrypt");
        int actions = sc.nextInt();
        switch (actions) {
            case 1:
                encryptor(encryptedList);
                break;
            case 2:
                decryptor(encryptedList, decryptedList);
                break;
        }
    }
    //Funktionen bryter upp en sträng till chars och lägger dem i en char Array, castar sedan till Int.
    //Funktionen ökar int värdet med 2 och castar sedan tillbaka till chars, lägger sedan ihop char arrayen till en sträng
    //och lägger det nu krypterade ordet till en arraylista.
    private static String encryptor(ArrayList<String> encryptedList) {
        System.out.println("text");
        String input = sc.next();
        char[] cArray = input.toCharArray();
        System.out.println(Arrays.toString(cArray));

        for (int i = 0; i < cArray.length; i++) {
            int ascii = (int) cArray[i];
            int newAscii = ascii + 2;
            cArray[i] = (char) newAscii;
        }
        String encryptedString = new String(cArray);
        System.out.println(Arrays.toString(cArray) + "\n" + encryptedString);
        encryptedList.add(encryptedString);
        return encryptedString;
    }
    //funktionen gör motsatt ifrån vad encryptor gör, alltså bryter upp och subtraherar 2 från int värdet.
    //Funktionen lägger sedan in det avkrypterade ordet i en Arraylista för avkrypterade ord.
    private static String decryptor(ArrayList<String> encryptedList, ArrayList<String> decryptedList) {
        System.out.println(encryptedList);
        System.out.println("which one to dekrypt (starts from 0)");
        int val = sc.nextInt();

        String str = encryptedList.get(val);
        char[] cArray = str.toCharArray();

        System.out.println(Arrays.toString(cArray));
        for (int i = 0; i < cArray.length; i++) {
            int ascii = (int) cArray[i];
            int newAscii = ascii - 2;
            cArray[i] = (char) newAscii;
        }
        String decryptedString = new String(cArray);
        decryptedList.add(decryptedString);
        System.out.println(Arrays.toString(cArray));
        return decryptedString;
    }



    //Lägger till ord i mainlist
    private static void addWord() {
        System.out.println("Submit a word to the list");
        String in = sc.nextLine();
        mainList.add(in);
        System.out.println(in + " got added to the list!"
                + "\n" + mainList);


    }

    //Söka efter ord i listan medhjälp av .contains. Har en switch som du också kan
    // använda för att ändra ett ord eller gå tillbaka till menyn.
    private static void searchList() {
        System.out.println("Search for a word");
        String in = sc.nextLine();
        if (mainList.contains(in)) {
            System.out.println("You searched for " + in + " and it exists");
            System.out.println(dateList.get((mainList.indexOf(in))));
            System.out.println("1: Change" + "\n" + "2: return to menu");
            int actions = sc.nextInt();
            //mainList.contains(in);
            switch (actions) {
                case 1:
                    changeString(in, mainList);
                    break;
                case 2:
                    break;
            }
        } else {
            System.out.println("It doesn't exist");
            searchList();
        }
    }
    //remove Strings. Denna metod tar bort strängar ur mainList om det du angivit finns i listan.
    private static void removeList() {
        System.out.println("What word do you want to delete?");
        String in = sc.nextLine();
        if (mainList.contains(in)) {
            System.out.println("You removed " + in);
            mainList.remove(in);
        } else {
            System.out.println("It doesn't exist");
        }
    }
    //Ändrar strings och lägger till de i slutet av listan.
    private static void changeString(String input, ArrayList<String> inputList) {
        inputList.remove(input);
        System.out.println("Input new a word");
        input = sc.next();
        inputList.add(input);
        System.out.println(inputList);
    }
    //Denna metod räknar antalet strings i mainList och kan skilja på strings medhjälp av mellanslag/whitespace.
    private static void countStrings() {
        if (mainList.size() == 0) {
            System.out.println("There are no words in the list");
        } else {
            //Stringbuilder lägger ihop till en sträng
            StringBuilder strings = new StringBuilder();
            //Loopar och räknar alla strings i listan
            for (String stringar : mainList) {
                //Lägger till ett mellanrum mellan varje sträng
                strings.append(stringar).append(" ");
            }
            String allWords = strings.toString();
            String str[] = allWords.split("\\s+");
            System.out.println("The list contains " + str.length + " words");
        }
    }
    //Menyn med alla alternativ.
    private static void menu() {
        System.out.println("\n" +
                "Menu" + "\n" + "\n" +
                "1: Add" + "\n" +
                "2: Search" + "\n" +
                "3: Delete" + "\n" +
                "4: Sorting" + "\n" +
                "5: Extra function" + "\n" +
                "6: Encrypt" + "\n" +
                "7: Count words" + "\n" +
                "8: Quit" + "\n");
    }
}
