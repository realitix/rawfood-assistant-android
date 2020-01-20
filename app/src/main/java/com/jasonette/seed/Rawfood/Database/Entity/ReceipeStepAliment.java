package com.jasonette.seed.Rawfood.Database.Entity;

import androidx.room.DatabaseView;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Relation;

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
    public int quantity;

    public ReceipeStepAliment() {}

    public ReceipeStepAliment(long alimentId, long stepId, int quantity) {
        this.alimentId = alimentId;
        this.stepId = stepId;
        this.quantity = quantity;
    }
}
