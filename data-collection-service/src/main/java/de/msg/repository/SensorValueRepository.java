package de.msg.repository;

import de.msg.model.SensorValue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * An simple {@link MongoRepository} to persist {@link SensorValue} data. In more sophisticated scenarios you would use a NoSQL database like Apache Cassandra.
 * See <a href="http://cassandra.apache.org/">http://cassandra.apache.org</a>
 */
@RepositoryRestResource
public interface SensorValueRepository extends MongoRepository<SensorValue, String> {

    /**
     * Finds {@link SensorValue} entities by <tt>car</tt> <tt>sensorName</tt> and <tt>sensorValue</tt>.
     *
     * @param car         The id of the car
     * @param sensorName  The name of the sensor
     * @param sensorValue The value of the sensor
     * @return The {@link SensorValue}
     */
    SensorValue findByCarAndSensorNameAndSensorValue(long car, String sensorName, String sensorValue);
}