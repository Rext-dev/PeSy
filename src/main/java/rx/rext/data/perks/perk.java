package rx.rext.data.perks;

public class perk {
    private double cooldown;
    private double maxCooldown;
    private double tokens;
    private double maxTokens;
    private double time; //time where the perk is enable
    private double maxTime; //time where the perk is enable
    private double level;

    public void addTokens(int tokens){
        this.tokens =+ tokens;
    }
    
    public double getTokens() {
        return tokens;
    }
    
    public void setTokens(double tokens) {
        this.tokens = tokens;
    }

    public double getMaxTokens() {
        return maxTokens;
    }

    public void addTime(int time){
        this.time =+ time;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getCooldown() {
        return cooldown;
    }

    public void setCooldown(double cooldown) {
        this.cooldown = cooldown;
    }

    public double getMaxCooldown() {
        return maxCooldown;
    }

    public void setMaxCooldown(double maxCooldown) {
        this.maxCooldown = maxCooldown;
    }

    public double getMaxTime() {
        return maxTime;
    }

    public void setMaxTime(double maxTime) {
        this.maxTime = maxTime;
    }

    public double getLevel() {
        return level;
    }

    public void setLevel(double level) {
        this.level = level;
    }


}
