package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "receipe_step_receipe", foreignKeys = {
        @ForeignKey(
                entity = Receipe.class,
                parentColumns = "id",
                childColumns = "receipeId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = ReceipeStep.class,
                parentColumns = "id",
                childColumns = "stepId",
                onDelete = ForeignKey.CASCADE
        )
})
public class ReceipeStepReceipe {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long receipeId;
    public long stepId;

    public ReceipeStepReceipe(int receipeId, int stepId) {
        this.receipeId = receipeId;
        this.stepId = stepId;
    }
}
