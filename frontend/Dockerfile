FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploytest/frontend
COPY package*.json ./
RUN npm install
COPY . .
RUN npm install --save-dev @babel/preset-env
RUN npm run build

EXPOSE 8080
CMD [ "http-server", "dist" ]
