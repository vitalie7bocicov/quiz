Start minikube with 2 CPUS, 2GB RAM, and enable autoscaler:
<br>
<code>
minikube start --cpus 2 --memory 2000 --driver=docker --extra-config=controller-manager.horizontal-pod-autoscaler-downscale-delay=1m
</code>

Alias for kubectl:
<br>
<code>
New-Alias -Name 'k' -Value 'kubectl'
</code>

<br>
Basic cmds:
<br> <code>kubectl get nodes</code>
<br> <code>kubectl get pod</code>
<br> <code>kubectl get services</code>
<br> <code>kubectl get deployment</code>

<br>Create deployment with docker image:
<code>kubectl create deployment $NAME --image=image</code>
<code>kubectl get replicaset</code>

Logs:
<code>kubectl logs $REPLICASET_NAME</code>

Terminal in pod:
<code>kubectl exec -it $POD_NAME -- /bin/bash</code>

Delete deployment:
<code>kubectl delete deployment $DEPLOYMENT_NAME</code>

Create deployment from file:
<code>kubectl apply -f config-file.yaml</code>

---

### Docker images for services

#### Step 1: add pom dependency

<pre>
    <code>
        <build>
                <plugins>
                    <plugin>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-maven-plugin</artifactId>
                        <configuration>
                            <image>
                                <name>
                                    vital7b/name-service:V1</name>
                            </image>
                            <pullPolicy>
                                IF_NOT_PRESENT
                            </pullPolicy>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
    </code>
</pre>

#### Step 2: make the image

<code>mvn spring-boot:build-image</code>

#### Step 3: push the image to docker.io

<code>
docker login
</code>
<code>
docker push vital7b/name-service:V1
</code>

#### Step 4: start docker container

<code>
docker run -p port:port --network host vital7b/name-service:V1
</code>

---

### Deploy service in k8s using a configuration file

<code>
kubectl apply -f postgres-service.yaml
</code>

###

To expose services in Minikube to your local machine, you can use the minikube tunnel command.

<br>
<code>
minikube tunnel
</code>

<pre>
<code>
# application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/domains
spring.datasource.username=postgres
spring.datasource.password=postgres
<code>
</pre>

<pre>
<code>
# application-k8s.properties

spring.datasource.url=jdbc:postgresql://minikube-ip:postgres-port/domains
spring.datasource.username=postgres
spring.datasource.password=postgres
<code>
</pre>

### To access from localhost the service running in the minikube cluster :

<code>
minikube service domain-service --url
</code>

minikube start --cpus 4 --memory 6000 --driver=docker --extra-config=controller-manager.horizontal-pod-autoscaler-downscale-delay=30s
minikube addons enable metrics-server

helm repo add prometheus-community https://prometheus-community.github.io/helm-charts
helm repo add grafana https://grafana.github.io/helm-charts
helm repo update

helm install prometheus prometheus-community/prometheus
helm install grafana grafana/grafana
helm install prometheus-operator prometheus-community/kube-prometheus-stack

k expose service prometheus-server --type=NodePort --target-port=9090 --name=prometheus-server-ext
k expose service grafana --type=NodePort --target-port=3000 --name=grafana-ext

minikube service prometheus-server-ext
minikube service grafana-ext

k get secret --namespace default grafana -o yaml

kubectl get secret --namespace default grafana -o jsonpath="{.data.admin-password}" | base64 --decode ; echo

kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
