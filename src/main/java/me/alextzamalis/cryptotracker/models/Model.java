package me.alextzamalis.cryptotracker.models;

import me.alextzamalis.cryptotracker.views.ViewFactory;

/**
 * Represents the core application model, managing the application's state and flow logic.
 * this class implements the Singleton design pattern to ensure that only the innstance of the model exists throughout the application life-cycle,
 * providing a single and consistent source of application data and operations
 */
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;

    private Model() {
        this.viewFactory = new ViewFactory();
    }

    /**
     * Returns the singleton instance of the Model class.
     * This method implements the Singleton design pattern, ensuring that only one instance
     * of Model is created and used throughout the application. The instance is lazily
     * initialized upon the first call to this method.
     * It is synchronized to ensure thread safety during the creation of the instance,
     * preventing multiple threads from concurrently creating different instances.
     *
     * @return The single, shared instance of the Model class.
     */
    public static synchronized Model getInstance() {
        if(model == null) {
            model = new Model();
        }
        return model;
    }

    /**
     * Returns the ViewFactory instance associated with this object.
     * This factory is typically used to create and manage view-related components.
     *
     * @return The ViewFactory instance.
     */
    public ViewFactory getViewFactory() {
        return viewFactory;
    }
}
