package com.beltser.lab.pattern;

import java.util.Scanner;

public class GuessNumber {
    public static void main(String[] args) {
        int correctAnswer = 10;
        new Riddle(
                new GuessInput(3),
                new SolutionVerifier(
                        new Diff(correctAnswer)
                )
        ).puzzle();
    }

    class A {
        void m() {

        }
    }

    class B extends A {
        @Override
        protected void m() {
            super.m();
        }
    }

    static class Riddle {
        private final GuessInput guessInput;
        //        private final int correctSolution;
        private final SolutionVerifier solutionVerifier;

        public Riddle(GuessInput guessInput, SolutionVerifier solutionVerifier) {
            this.guessInput = guessInput;
//            this.correctSolution = correctSolution;
            this.solutionVerifier = solutionVerifier;
        }

        public boolean puzzle() {
            boolean isCorrect;
            boolean guessAvailable;
            do {
                int guess = guessInput.guess();
                isCorrect = solutionVerifier.verify(guess);
                guessAvailable = guessInput.isGuessAvailable();
                System.out.println(solutionVerifier.prompt(guess));
            } while (!isCorrect && guessAvailable);
            System.out.println(isCorrect ? "Correct!" : "Wrong");
            return isCorrect;
        }
    }

    static class GuessInput {
        private static final Scanner scanner = new Scanner(System.in);
        private final int maxAttempts;
        private int attemptNumber;

        public GuessInput(int maxAttempts) {
            this.maxAttempts = maxAttempts;
        }

        public int guess() {
            return guess("Guess: ");
        }

        public int guess(String prompt) {
            if (!isGuessAvailable()) {
                System.err.println("You already spend all attempts");
                return 0;
            }
            attemptNumber++;
            System.out.println("Attempt number " + attemptNumber);
            System.out.println(prompt);
            return scanner.nextInt();
        }

        public boolean isGuessAvailable() {
            return attemptNumber < maxAttempts;
        }

    }

    private static class SolutionVerifier {
        private final Diff diff;
//        private final int correctSolution;

        public SolutionVerifier(Diff diff) {
            this.diff = diff;
//            this.correctSolution = correctSolution;
        }

        public boolean verify(int guess) {
            return diff.guess(guess).isCorrect();
        }

        public String prompt(int guess) {
            return diff.guess(guess).getDifference() > 0 ? "Too big. " : "Too small. ";
        }
    }

    private static class Diff {
        private final int correctSolution;

        public Diff(int correctSolution) {
            this.correctSolution = correctSolution;
        }

        public GuessResult guess(int guess) {
            int difference = guess - correctSolution;
            return new GuessResult(guess, difference, difference == 0);
        }

        private final class GuessResult {
            private final int guess;
            private final int difference;
            private final boolean isCorrect;

            public GuessResult(int guess, int difference, boolean isCorrect) {
                this.guess = guess;
                this.difference = difference;
                this.isCorrect = isCorrect;
            }

            public int getGuess() {
                return guess;
            }

            public int getDifference() {
                return difference;
            }

            public boolean isCorrect() {
                return isCorrect;
            }
        }
    }
}
