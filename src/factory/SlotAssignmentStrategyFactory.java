package factory;

import models.SlotAssignmentStrategyType;
import strategies.RandomSlotAssignmentStrategy;
import strategies.SlotAssignementStrategy;

public class SlotAssignmentStrategyFactory {
    public static SlotAssignementStrategy getSlotForType(SlotAssignmentStrategyType slotAssignmentStrategyType){
        return new RandomSlotAssignmentStrategy();
    }
}
