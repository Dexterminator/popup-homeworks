package se.dxtr.satisfiability;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

        public boolean getBooleanValue(boolean assignedValue) {
            return variableValue.apply(assignedValue);
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

    public static void main(String[] args) throws IOException {
        int cases = io.getInt();
        for (int i = 0; i < cases; i++)
            solveInstance();
        io.close();
    }

    private static void solveInstance() throws IOException {
        int variablesCount = io.getInt();
        int clauseCount = io.getInt();
        List<Variable[]> clauses = getClauses(clauseCount);
        new Instance(clauses, variablesCount).solve();
    }

    private static List<Variable[]> getClauses(int clauseCount) throws IOException {
        List<Variable[]> clauses = new ArrayList<>();
        for (int j = 0; j < clauseCount; j++) {
            String line = io.getLine();
            Variable[] clause = parseClause(line);
            clauses.add(clause);
        }
        return clauses;
    }

    private static Variable[] parseClause(String line) {
        String[] split = line.split(" v ");
        Variable[] clause = new Variable[split.length];
        for (int k = 0; k < split.length; k++) {
            String s = split[k];
            if (s.startsWith("~")) {
                clause[k] = new Variable(Integer.parseInt(s.substring(2, s.length())) - 1, not);
            } else {
                clause[k] = new Variable(Integer.parseInt(s.substring(1, s.length())) - 1, Function.identity());
            }
        }
        return clause;
    }

    static class Instance {
        private boolean satisfiable = false;
        private final List<Variable[]> clauses;
        private final boolean[] assignment;

        public Instance(List<Variable[]> clauses, int variablesCount) {
            this.clauses = clauses;
            this.assignment = new boolean[variablesCount];
        }

        public void solve() {
            testAssignment(0);
            if (satisfiable) {
                io.println("satisfiable");
            } else {
                io.println("unsatisfiable");
            }
        }

        private void testAssignment(int k) {
            if (satisfiable)
                return;

            if (k == assignment.length) {
                for (Variable[] clause : clauses) {
                    boolean satisfied = isSatisfied(clause);
                    if (!satisfied)
                        return;
                }
                // All clauses are satisfied
                satisfiable = true;
                return;
            }

            testAssignment(k + 1);
            assignment[k] = true;
            testAssignment(k + 1);
            assignment[k] = false;
        }

        private boolean isSatisfied(Variable[] clause) {
            for (Variable variable : clause) {
                boolean assignedValue = assignment[variable.id];
                if (variable.getBooleanValue(assignedValue)) {
                    return true;
                }
            }
            // No variable was true, not satisfied
            return false;
        }
    }
}
