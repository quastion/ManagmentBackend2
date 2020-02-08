package com.opencode.managment.app.factory;

public class Factory {
    public static final int COST_OF_CONVERSION_1_ESM_TO_1_EGP = 2000;
    public static final int COST_OF_CONVERSION_2_ESM_TO_2_EGP = 3000;
    public static final int COST_OF_BUILDING_STANDARD_FACTORY = 5000;
    public static final int COST_OF_BUILDING_AUTOMATED_FACTORY = 10000;
    public static final int COST_OF_RECONSTRUCTION_FACTORY = 9000;
    public static final int BUILD_TIME_STANDARD_FACTORY = 5;
    public static final int BUILD_TIME_AUTOMATED_FACTORY = 7;
    public static final int BUILD_TIME_RECONSTRUCTION_STANDARD_FACTORY = 9;

    private FactoryType factoryType;
    private State state;
    private int buildProgress;
    private int conversionProgress;

    public static enum FactoryType{
        STANDARD, AUTOMATED
    }

    public static enum State{
        IN_BUILDING, BUILT, IN_CONVERSION, CONVERTED
    }

    public Factory(FactoryType factoryType){
        this.factoryType = factoryType;
        state = State.IN_BUILDING;
    }

    public void updateState(){
        switch (state){
            case IN_BUILDING:
                buildProgress++;
                if(factoryType == FactoryType.STANDARD && buildProgress >= BUILD_TIME_STANDARD_FACTORY){
                    state = State.BUILT;
                }
                else if(factoryType == FactoryType.AUTOMATED && buildProgress >= BUILD_TIME_AUTOMATED_FACTORY){
                    state = State.BUILT;
                }
                break;
            case IN_CONVERSION:
                conversionProgress++;
                if(conversionProgress >= BUILD_TIME_RECONSTRUCTION_STANDARD_FACTORY){
                    state = State.CONVERTED;
                }
                break;
        }
    }

    /**
     * Модернизировать фабрику
     */
    public void convertFactory(){
        if(factoryType == FactoryType.STANDARD){
            state = State.IN_CONVERSION;
        }
    }

    public State getState() {
        return state;
    }

    public FactoryType getFactoryType() {
        return factoryType;
    }
}
