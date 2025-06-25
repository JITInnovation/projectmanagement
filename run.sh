#!/bin/bash

# Create classpath with all dependencies
classpath="target/classes:"
for jar in target/dependency/*.jar; do
    classpath="$classpath:$jar"
done

# Run the application
java -cp "$classpath" com.projectmanagement.ProjectManagementSystemApplication
