package ohtu;

public class Player {

  private String name;
  private String team;
  private Integer goals;
  private Integer assists;
  private String nationality;

  public Integer getGoals() {
    return this.goals;
  }

  public void setGoals(Integer goals) {
    this.goals = goals;
  }

  public String getNationality() {
    return this.nationality;
  }

  public Integer getAssists() {
    return this.assists;
  }

  public void setAssists(Integer assists) {
    this.assists = assists;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public String getTeam() {
    return this.team;
  }

  public Integer getGoalsAndAssists() {
    return goals + assists;
  }

  public void setTeam(String team) {
    this.team = team;
  }

  //Henrik Borgstrom team FLA goals 0 assists 0
  @Override
  public String toString() {
    return (
      name +
      "\t" +
      this.getTeam() +
      " \t" +
      Integer.toString(this.getGoals() + this.getAssists())
    );
  }
}
