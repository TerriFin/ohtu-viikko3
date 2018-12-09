package statistics.matcher;

import statistics.Player;

public class And implements Matcher {

    private Matcher[] matchers;

    public And(Matcher... matchers) {
        this.matchers = matchers;
    }

    public void addMatcher(Matcher matcher) {
        Matcher[] newMatchers = new Matcher[matchers.length + 1];

        for (int i = 0; i < matchers.length; i++) {
            newMatchers[i] = matchers[i];
        }

        newMatchers[newMatchers.length - 1] = matcher;
    }

    @Override
    public boolean matches(Player p) {
        for (Matcher matcher : matchers) {
            if (!matcher.matches(p)) {
                return false;
            }
        }

        return true;
    }
}
