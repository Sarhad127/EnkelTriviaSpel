package mittSpel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Quiz implements ActionListener {
    String[] questions;
    String[][] options;
    char[] answers;
    char guess;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions;
    public void myQuestions() throws IOException {
        int lineCountQuestions = 0;
        BufferedReader readerQuestions = new BufferedReader(new FileReader("src/mittSpel/questions.txt"));
        String line;
        while ((line = readerQuestions.readLine()) != null) {
            lineCountQuestions++;
        }
        readerQuestions.close();
        questions = new String[lineCountQuestions];
        readerQuestions = new BufferedReader(new FileReader("src/mittSpel/questions.txt"));
        int index = 0;
        while ((line = readerQuestions.readLine()) != null) {
            questions[index] = line;
            index++;
        }
        readerQuestions.close();
        total_questions = questions.length;
    }
    public String[][] myOptions() throws IOException {
        List<String[]> optionsList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/mittSpel/options.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] allOptions = line.split(",");
            if (allOptions.length == 4) {
                optionsList.add(allOptions);
            }
        }
        reader.close();
        String[][] optionsArray = new String[optionsList.size()][];
        for (int i = 0; i < optionsList.size(); i++) {
            optionsArray[i] = optionsList.get(i);
        }
        return optionsArray;
    }
    public char[] myAnswers() throws IOException {
        List<Character> answersList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader("src/mittSpel/answers.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] answers = line.split(",");
            for (String ans : answers) {
                if (ans.trim().length() > 0) {
                    answersList.add(ans.trim().charAt(0));
                }
            }
        }
        reader.close();
        char[] answersArray = new char[answersList.size()];
        for (int i = 0; i < answersList.size(); i++) {
            answersArray[i] = answersList.get(i);
        }
        return answersArray;
    }
    int result;
    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();
    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    JTextField number_right = new JTextField();
    JTextField percentage = new JTextField();
    public Quiz() throws IOException {
        myQuestions();
        options = myOptions();
        answers = myAnswers();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setTitle("Trivia");

        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free", Font.PLAIN, 30));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);

        textArea.setBounds(0,50,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(25,25,25));
        textArea.setForeground(new Color(25,255,0));
        textArea.setFont(new Font("Calibri", Font.PLAIN, 25));
        textArea.setBorder(BorderFactory.createBevelBorder(1));
        textArea.setEditable(false);

        buttonA.setBounds(0,100,100,100);
        buttonA.setFont(new Font("Calibri", Font.PLAIN, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0,200,100,100);
        buttonB.setFont(new Font("Calibri", Font.PLAIN, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0,300,100,100);
        buttonC.setFont(new Font("Calibri", Font.PLAIN, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0,400,100,100);
        buttonD.setFont(new Font("Calibri", Font.PLAIN, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setBackground(new Color(50,50,50));
        answer_labelA.setForeground(new Color(25,255,0));
        answer_labelA.setFont(new Font("Calibri", Font.PLAIN, 35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setBackground(new Color(50,50,50));
        answer_labelB.setForeground(new Color(25,255,0));
        answer_labelB.setFont(new Font("Calibri", Font.PLAIN, 35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setBackground(new Color(50,50,50));
        answer_labelC.setForeground(new Color(25,255,0));
        answer_labelC.setFont(new Font("Calibri", Font.PLAIN, 35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setBackground(new Color(50,50,50));
        answer_labelD.setForeground(new Color(25,255,0));
        answer_labelD.setFont(new Font("Calibri", Font.PLAIN, 35));

        number_right.setBounds(225,225,200,100);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color(25,255,0));
        number_right.setFont(new Font("Calibri", Font.PLAIN, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        percentage.setBounds(225,325,200,100);
        percentage.setBackground(new Color(25,25,25));
        percentage.setForeground(new Color(25,255,0));
        percentage.setFont(new Font("Calibri", Font.PLAIN, 50));
        percentage.setBorder(BorderFactory.createBevelBorder(1));
        percentage.setHorizontalAlignment(JTextField.CENTER);
        percentage.setEditable(false);

        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textField);
        frame.add(textArea);
        frame.setVisible(true);

        nextQuestion();
    }
    public void nextQuestion(){
        if (index >= total_questions){
            results();
        }
        else {
            textField.setText("Question " + (index + 1));
            textArea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (e.getSource()==buttonA){
            answer ='A';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonB){
            answer ='B';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonC){
            answer ='C';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }
        if (e.getSource()==buttonD){
            answer ='D';
            if (answer == answers[index]){
                correct_guesses++;
            }
        }
        displayAnswer();
    }
    public void displayAnswer(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index] != 'A'){
            answer_labelA.setForeground(new Color(255,0,0));
        }
        if (answers[index] != 'B'){
            answer_labelB.setForeground(new Color(255,0,0));
        }
        if (answers[index] != 'C'){
            answer_labelC.setForeground(new Color(255,0,0));
        }
        if (answers[index] != 'D'){
            answer_labelD.setForeground(new Color(255,0,0));
        }

        Timer pause = new Timer(2000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(25,255,0));
                answer_labelB.setForeground(new Color(25,255,0));
                answer_labelC.setForeground(new Color(25,255,0));
                answer_labelD.setForeground(new Color(25,255,0));

                answer = ' ';
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);
        result = (int)((correct_guesses/(double)total_questions)*100);
        textField.setText("RESULTS");
        textArea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");
        number_right.setText("("+correct_guesses + "/" + total_questions+")");
        percentage.setText(result + "%");
        frame.add(percentage);
        frame.add(number_right);
    }
    public static void main(String[] args) throws IOException {
        Quiz quiz = new Quiz();
    }
}