package ai.openfabric.api.model;


import com.github.dockerjava.api.model.Container;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity()
public class Worker extends Datable implements Serializable {
    public final Container container;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "of-uuid")
    @GenericGenerator(name = "of-uuid", strategy = "ai.openfabric.api.model.IDGenerator")
    @Getter
    @Setter
    public String id;

    public String name;

    protected Worker() {
        this.container = null;
    }

    protected Worker(Container container) {
        this.container = container;

        this.name = container.getId();
    }

}
