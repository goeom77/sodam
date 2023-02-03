FROM node:16.15.0 as build-stage
RUN npm install -g http-server
WORKDIR /var/jenkins_home/workspace/deploytest/frontend
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
EXPOSE 8180
CMD [ "http-server","-p","8180", "dist" ]

