package statistics.matcher;

public class QueryBuilder {

  Matcher m;

  public QueryBuilder() {
    this.m = new All();
  }

  public QueryBuilder playsIn(String team) {
    this.m = new And(this.m, new PlaysIn(team));
    return this;
  }

  public QueryBuilder hasAtLeast(int value, String category) {
    this.m = new And(this.m, new HasAtLeast(value, category));
    return this;
  }

  public QueryBuilder hasFewerThan(int value, String category) {
    this.m = new And(this.m, new HasFewerThan(value, category));
    return this;
  }

  public Matcher build() {
    return this.m;
  }
}
