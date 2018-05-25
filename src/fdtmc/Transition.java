package fdtmc;

public class Transition {

	private String actionName;
	private String probability;
	private State source, target;

	public Transition(State source, State target, String actionName, String probability) {
		this.source = source;
		this.target = target;
		this.actionName = actionName;
		this.probability = probability;
	}

	public String getActionName() {
		return actionName;
	}

	public String getProbability() {
		return probability;
	}

	public State getSource() {
		return source;
	}

	public State getTarget() {
		return target;
	}
	
	private boolean notNullTransition(Object obj) {
		return obj != null && obj instanceof Transition;
	}
	
	private boolean areEqualSources(State source) {
		return this.getSource().equals(source);
	}
	
	private boolean areEqualTargets(State target) {
		return this.getTarget().equals(target);
	}

	private boolean equalityOfTransition(State source, State target, String probability) {
		return areEqualSources(source) && areEqualTargets(target) && areEqualProbabilities(probability);
	}
    /**
     * Two transitions are equal if they have equal source and target states.
     * Moreover, their transition probabilities must be equal numbers or
     * be both (not necessarily equal) variable names.
     */
    @Override
    public boolean equals(Object obj) {
        if (notNullTransition(obj)) {
            Transition other = (Transition) obj;
            return equalityOfTransition(other.getSource(), other.getTarget(), other.getProbability());
        }
        return false;
    }
    
    private boolean statesAreEquals (Transition transition) {
    	return source.equals(transition.source)
                && target.equals(transition.target)
                && areEqualProbabilities(probability);
    }

    @Override
    public int hashCode() {
        return this.source.hashCode() + this.target.hashCode();
    }

    /**
     * Returns true if {@code p1} and {@code p2} are equal double values.
     * If they contain variable names, even different ones, the result is also true.
     * @param p1
     * @param p2
     * @return
     */
    private boolean areEqualProbabilities(String p2) {
    	String p1 = this.getProbability();
        double prob1 = 0;
        double prob2 = 0;
        boolean isVariable = false;
        try {
            prob1 = Double.parseDouble(p1);
        } catch (NumberFormatException e) {
            isVariable = true;
        }
        try {
            prob2 = Double.parseDouble(p2);
        } catch (NumberFormatException e) {
            if (isVariable) {
                return true;
            }
        }
        return prob1 == prob2;
    }
}
