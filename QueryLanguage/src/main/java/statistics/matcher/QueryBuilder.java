/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author samisaukkonen
 */
public class QueryBuilder {
    private Matcher matcher;
    
    public QueryBuilder() {
        this.matcher = new And(new All());
    }
    
    public Matcher build() {
        return matcher;
    }
    
    public QueryBuilder playsIn(String input) {
        matcher = new And(matcher, new PlaysIn(input));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int howMany, String input) {
        matcher = new And(matcher, new HasAtLeast(howMany, input));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int howMany, String input) {
        matcher = new And(matcher, new HasAtLeast(howMany, input));
        return this;
    }
    
}
