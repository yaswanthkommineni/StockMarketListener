# Step 1: Use an official Node.js image as the base image
FROM node:18 AS build

# Step 2: Set the working directory in the container
WORKDIR /app

# Step 3: Copy package.json and package-lock.json
COPY package*.json ./

# Step 4: Install dependencies
RUN npm install

# Step 5: Copy the rest of the application files
COPY . .

# Step 6: Build the project
RUN npm run build

# Step 7: Set up a lightweight web server to serve the built files
FROM nginx:alpine

# Step 8: Copy the build output from the previous build stage to nginx's html directory
COPY --from=build /app/dist /usr/share/nginx/html

# Step 9: Expose the default HTTP port
EXPOSE 80

# Step 10: Start the nginx server
CMD ["nginx", "-g", "daemon off;"]