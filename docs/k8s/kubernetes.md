# KUBERNETES 101

---

## Kubernetes Components

---

<p>
    <b>POD<b> 
    <br>
    <img src="images/pod.png" alt="pod" width="300"/>
    <br>- smallest unit of K8S 
    <br>- abstraction over container
    <br>- usually 1 application per Pod
    <br>- each pod has its own IP address (internal)       
    <br>- auto re-creation on crash
<p>

<p>
    <b>SERVICE<b> 
    <br>
    <img src="images/service.png" alt="pod" width="700"/>
    <br>- permanent IP address
    <br>- load balancer
<p>

<p>
    <b>DEPLOYMENT<b> 
    <br>
    <img src="images/deployment.png" alt="pod" width="700"/>
    <br>- abstraction over pods
    <br>- blueprint for app pods
<p>

## Kubernetes Architecture

<p>
Master nodes <br>
Worker nodes <br>

<img src="images/cluster-setup.png" alt="pod" width="1000"/>
***

## Minikube 101

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

#### Step 1: Create the deployment config file

<pre>
<code>
apiVersion: apps/v1
kind: Deployment
metadata:
  name: domain-depl
  labels:
    app: domain
spec:
  replicas: 2
  selector:
    matchLabels:
      app: domain
  strategy:
    rollingUpdate:
      maxSurge: 25%
      maxUnavailable: 25%
    type: RollingUpdate
  template:
    metadata:
      labels:
        app: domain
    spec:
      containers:
        - image: vital7b/domain-service:V1
          ports:
            - containerPort: 5000
          imagePullPolicy: IfNotPresent
          name: frontend-demo
          resources:
            requests:
              cpu: 250m
              memory: 128Mi
      dnsPolicy: ClusterFirst
      restartPolicy: Always
      schedulerName: default-scheduler
      terminationGracePeriodSeconds: 30

---
apiVersion: v1
kind: Service
metadata:
  name: domain-service
spec:
  selector:
    app: domain
  ports:
    - protocol: TCP
      port: 5000
      targetPort: 5000
  type: LoadBalancer

</code>
</pre>

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
