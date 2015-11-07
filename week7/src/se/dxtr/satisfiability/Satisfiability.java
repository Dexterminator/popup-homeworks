package se.dxtr.satisfiability;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * Created by dexter on 07/11/15.
 */
public class Satisfiability {
    static class Variable {
        public int id;
        public Function<Boolean, Boolean> variableValue;

        public Variable(int id, Function<Boolean, Boolean> variableValue) {
            this.id = id;
            this.variableValue = variableValue;
        }

        @Override
        public String toString() {
            return "Variable{" +
                    "id=" + id +
                    ", variableValue=" + variableValue.apply(true) +
                    '}';
        }
    }

    static Function<Boolean, Boolean> not = b -> !b;
    static Kattio io = new Kattio(System.in, System.out);
    static boolean satisfiable;

    public static void main(String[] args) throws IOException {
        int cases = io.getInt();
        for (int i = 0; i < cases; i++) {
            handleCase();
        }
        io.close();
    }

    private static void handleCase() throws IOException {
//                    System.err.println("case " + i);
        int variablesCount = io.getInt();
        int clauseCount = io.getInt();
        List<Variable[]> clauses = new ArrayList<>();
        for (int j = 0; j < clauseCount; j++) {
            String line = io.getLine();
            String[] split = line.split(" v ");
            Variable[] clause = new Variable[split.length];
            parseClause(split, clause);
            clauses.add(clause);
        }
        satisfiable = false;
        solve(variablesCount, clauses);
    }

    private static void parseClause(String[] split, Variable[] clause) {
        for (int k = 0; k < split.length; k++) {
            String s = split[k];
            if (s.startsWith("~")) {
                clause[k] = new Variable(Integer.parseInt(s.substring(2, 3)) - 1, not);
            } else {
                clause[k] = new Variable(Integer.parseInt(s.substring(1, 2)) - 1, Function.identity());
            }
        }
    }

    private static void solve(int variablesCount, List<Variable[]> clauses) {
        boolean[] assignment = new boolean[variablesCount];
        testAssignment(0, clauses, assignment);
        if (satisfiable) {
            io.println("satisfiable");
        } else {
            io.println("unsatisfiable");
        }
    }

    private static void testAssignment(int k, List<Variable[]> clauses, boolean[] assignment) {
        if (satisfiable)
            return;

        if (k == assignment.length) {
            System.err.println("");
            System.err.println("assignment = " + Arrays.toString(assignment));
            for (Variable[] clause : clauses) {
                boolean satisfied = isSatisfied(assignment, clause);
                System.err.println("satisfied = " + satisfied);
                if (!satisfied)
                    return;
            }
            // All clauses are satisfied
            satisfiable = true;
            return;
        }

        testAssignment(k + 1, clauses, assignment);
        assignment[k] = true;
        testAssignment(k + 1, clauses, assignment);
        assignment[k] = false;
    }

    private static boolean isSatisfied(boolean[] assignment, Variable[] clause) {
        System.err.println("clause = " + Arrays.toString(clause));
        for (Variable variable : clause) {
            boolean assignedValue = assignment[variable.id];
            if (variable.variableValue.apply(assignedValue))
                return true;
        }
        // No variable was true
        return false;
    }
}
