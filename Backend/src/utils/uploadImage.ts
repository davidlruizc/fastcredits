import { Storage } from '@google-cloud/storage';
import { writeFileSync, unlinkSync } from 'fs';

export const UploadImage = async (
  name: string,
  folder: string,
  base64: string,
): Promise<string> => {
  const storage = new Storage({ keyFilename: './google-cloud-key.json' });
  const bucketname = 'tulan-trega';
  const fileName = `${Date.now()}-${name}`;

  writeFileSync(`./${fileName}`, base64, 'base64');

  const uploaded = await storage
    .bucket(bucketname)
    .upload(`./${fileName}`, { destination: `${folder}/${fileName}` });

  const url = `https://storage.googleapis.com/${bucketname}/${uploaded[0].metadata.name}`;

  await storage.bucket(bucketname).file(`${folder}/${fileName}`).makePublic();
  unlinkSync(`./${fileName}`);

  return url;
};

export const DeleteImage = async (name: string) => {
  const storage = new Storage({ keyFilename: './google-cloud-key.json' });
  const bucketname = 'tulan-trega';

  await storage.bucket(bucketname).file(name).delete({ ignoreNotFound: true });
};
