# Use a lightweight Python image as the base
FROM python:3.11-slim-buster

# Set the working directory
WORKDIR /app

# Copy the requirements file
COPY requirements.txt requirements.txt

RUN pip install -r requirements.txt

# Copy the application code
COPY . .

# Define the command to run your application
CMD ["python", "producerScript.py"]