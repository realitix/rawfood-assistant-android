package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = Aliment.class,
                parentColumns = "id",
                childColumns = "alimentId",
                onDelete = ForeignKey.CASCADE
        ),
        @ForeignKey(
                entity = ReceipeStep.class,
                parentColumns = "id",
                childColumns = "stepId",
                onDelete = ForeignKey.CASCADE
        )
})
public class ReceipeStepAliment {
    @PrimaryKey(autoGenerate = true)
    public long id;
    public long alimentId;
    public long stepId;

    public ReceipeStepAliment(int alimentId, int stepId) {
        this.alimentId = alimentId;
        this.stepId = stepId;
    }
}
