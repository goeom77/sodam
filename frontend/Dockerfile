FROM node:16.15.0 as build-stage
WORKDIR /var/jenkins_home/workspace/deploytest/frontend
COPY package*.json ./
RUN npm install
COPY . .
RUN npm install --save-dev @babel/preset-env
RUN npm run build
FROM nginx:stable-alpine as production-stage

COPY --from=build-stage /var/jenkins_home/workspace/deploytest/frontend/dist /usr/share/nginx/html
#COPY --from=build-stage /var/jenkins_home/workspace/deploytest/frontend/deploy_conf/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 8080
CMD ["nginx", "-g","daemon off;"]